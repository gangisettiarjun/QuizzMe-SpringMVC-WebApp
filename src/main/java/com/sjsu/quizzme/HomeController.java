package com.sjsu.quizzme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.quizzme.model.Category;
import com.sjsu.quizzme.model.Comments;
import com.sjsu.quizzme.model.CommentsDTO;
import com.sjsu.quizzme.model.Login;
import com.sjsu.quizzme.model.Questions;
import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Recommendation;
import com.sjsu.quizzme.model.Result;
import com.sjsu.quizzme.model.ScoreCard;
import com.sjsu.quizzme.model.User;
import com.sjsu.quizzme.services.CategoryServices;
import com.sjsu.quizzme.services.CommentsServices;
import com.sjsu.quizzme.services.QuizServices;
import com.sjsu.quizzme.services.RecommendationService;
import com.sjsu.quizzme.services.ScoreCardServices;
import com.sjsu.quizzme.services.UserServices;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private UserServices userSer;
	private QuizServices quizSer;

	@Autowired
	private RecommendationService recSer;

	@Autowired
	private CategoryServices categorySer;

	@Autowired
	private ScoreCardServices scoreCardSer;

	@Autowired
	private CommentsServices comSer;

	@Autowired(required = true)
	@Qualifier(value = "userServiceBean")
	public void setUserService(UserServices userService) {
		this.userSer = userService;
	}

	@Autowired(required = true)
	@Qualifier(value = "quizServiceBean")
	public void setQuizService(QuizServices quizService) {
		this.quizSer = quizService;
	}

	/**
	 * Validate the user and navigate to personalize dashboard
	 * 
	 * @param credentials
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(@ModelAttribute("Login") Login credentials,
			HttpServletRequest request, Model model) {
		User validatedUser = userSer.Login(credentials);
		if (validatedUser == null) {
			Login modelLogin = new Login();
			List<ScoreCard> topScorers = scoreCardSer.getTopScorers();
			HashMap<String, Integer> categoryDistribution = quizSer
					.getCategoryWiseList();
			modelLogin.setUsername("");
			modelLogin.setPassword("");
			model.addAttribute("loginModel", modelLogin);
			model.addAttribute("topScores", topScorers);
			model.addAttribute("categoryDistribution", categoryDistribution);
			return "loginFail";
		}
		List<ScoreCard> attemptedQuizzes = scoreCardSer
				.getAttemptedQuizes(validatedUser.getCards());
		HashMap<String, Integer> categoryDistribution = scoreCardSer
				.getCategoryCountForAttemptedQuizzes(validatedUser.getCards());

		request.getSession().setAttribute("loggedInUser", validatedUser);
		List<Quiz> popularQuizzes = quizSer.getMostPopularQuizes();
		model.addAttribute("userName", validatedUser.getFirstName());
		model.addAttribute("scoreCards", validatedUser.getCards());
		model.addAttribute("popularQuizList", popularQuizzes);
		model.addAttribute("attemptedQuizzes", attemptedQuizzes);
		model.addAttribute("categoryWiseDistribution", categoryDistribution);
		return "userDashboard";

	}

	// Redirects to login page when session expires or a user is not allowed to
	// access some page

	@RequestMapping(value = "/pleaseLogin", method = RequestMethod.POST)
	public String pleaseLogin(Model model, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		return "redirect:/";
	}

	/**
	 * Gets the user dashboard if user is not logged in will direct to the login
	 * page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request, Model model) {
		User validatedUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (validatedUser == null) {
			return "redirect:/";
		}
		List<ScoreCard> attemptedQuizzes = scoreCardSer
				.getAttemptedQuizes(validatedUser.getCards());
		HashMap<String, Integer> categoryDistribution = scoreCardSer
				.getCategoryCountForAttemptedQuizzes(validatedUser.getCards());
		List<Quiz> popularQuizzes = quizSer.getMostPopularQuizes();
		model.addAttribute("userName", validatedUser.getFirstName());
		model.addAttribute("scoreCards", validatedUser.getCards());
		model.addAttribute("popularQuizList", popularQuizzes);
		model.addAttribute("attemptedQuizzes", attemptedQuizzes);
		model.addAttribute("categoryWiseDistribution", categoryDistribution);
		return "userDashboard";

	}

	/**
	 * Get the view to comment on a quiz
	 * 
	 * @param quizID
	 *            :quiz on which the user decides to comment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quizzes/comment/{quizId}", method = RequestMethod.GET)
	public String writeComments(@PathVariable(value = "quizId") Integer quizID,
			Model model, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		Quiz quizToCommentOn = quizSer.getQuiz(quizID);
		Comments newComment = new Comments();
		model.addAttribute("quiz", quizToCommentOn);
		model.addAttribute("comment", newComment);
		return "commentOnQuiz";
	}

	/**
	 * Post a comment on a particular quiz
	 * 
	 * @param comment
	 *            : comment by user
	 * @param userID
	 *            : user Id
	 * @param quizID
	 *            : quiz on which the commend is being made.
	 * @return
	 */
	@RequestMapping(value = "/quizzes/comment", method = RequestMethod.POST)
	public String writeComments(@ModelAttribute("comment") Comments newComment,
			@RequestParam(value = "quizId", required = true) Integer quizId,
			HttpServletRequest request) {

		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		if (newComment == null) {
			return "defaultError";
		}
		int userId = currentUser.getId();
		comSer.createComments(userId, quizId, newComment.getComment());
		return "redirect:/quiz/search";
	}

	/**
	 * Get the view to recommend a quiz
	 * 
	 * @param quizID
	 *            :quiz on which the user decides to recommend
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quizzes/recommend/{quizId}", method = RequestMethod.GET)
	public String recommendQuiz(@PathVariable(value = "quizId") Integer quizID,
			Model model, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		Quiz quizToCommentOn = quizSer.getQuiz(quizID);
		model.addAttribute("quiz", quizToCommentOn);
		return "recomendations";
	}

	/**
	 * Post a recommendation for a particular quiz
	 * 
	 * @param recommendeeEmail
	 * @param quizId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/quizzes/recommend", method = RequestMethod.POST)
	public String writeRecommendations(
			@RequestParam(value = "recommendeeEmail", required = true) String recommendeeEmail,
			@RequestParam(value = "quizId", required = true) Integer quizId,
			HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		int userId = currentUser.getId();
		recSer.createRecommendation(userId, recommendeeEmail, quizId);
		return "redirect:/dashboard";
	}

	/**
	 * Get recommendations for the logged in user
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/recommendations", method = RequestMethod.GET)
	public String getRecommendationsForMe(Model model,
			HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		Set<Recommendation> recoForUser = currentUser
				.getRecommendationsForUser();
		List<Recommendation> newList = new ArrayList<Recommendation>();
		for (Iterator<Recommendation> it = recoForUser.iterator(); it.hasNext();) {
			Recommendation reco = it.next();
			newList.add(reco);
		}
		model.addAttribute("recommendations", newList);
		return "recomendationsForUser";
	}

	/**
	 * Attempt a quiz and return a score card
	 * 
	 * @param attemptedQuiz
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quizzes/attempt", method = RequestMethod.POST)
	public String attempQuiz(@RequestParam("count") Integer currentCount,
			@RequestParam("quizId") Integer quizId, HttpServletRequest request,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		if (scoreCardSer.checkIfUserHasAttemptedQuiz(currentUser, quizId)) {
			return "duplicateAttempt";
		}
		// get the quiz and store it in the session
		Quiz quizToAttempt = quizSer.getQuiz(quizId);
		request.getSession().setAttribute("quizBeingAnswered", quizToAttempt);
		Set<Questions> questions = quizToAttempt.getQuestions();
		List<Questions> questionsToAttempt = new ArrayList<Questions>();
		for (Iterator<Questions> it = questions.iterator(); it.hasNext();) {
			Questions currentQuestion = it.next();
			questionsToAttempt.add(currentQuestion);
		}
		request.getSession().setAttribute("questionsBeingAnswered",
				questionsToAttempt);
		// get the current question object and send it to the jsp
		Questions currentQuestion = questionsToAttempt.get(currentCount);
		model.addAttribute("question", currentQuestion);
		model.addAttribute("quiz", quizToAttempt);
		model.addAttribute("currentCount", currentCount + 1);
		if (currentCount == quizToAttempt.getQuestions().size()) {
			model.addAttribute("isLastQuestion", 1);
		} else {
			model.addAttribute("isLastQuestion", 0);
		}
		return "question";

	}

	/**
	 * Post answer of a question and show the user next question. If the quiz is
	 * over show the user his score and the current highest score
	 * 
	 * @param question
	 * @param isLastQuestion
	 * @param currentCount
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quizzes/postAnswer", method = RequestMethod.POST)
	public String postQuestion(@ModelAttribute("question") Questions question,
			@RequestParam("isLastQuestion") Integer isLastQuestion,
			@RequestParam("count") Integer currentCount,
			HttpServletRequest request, Model model) {
		// add to session and redirect to next question view
		@SuppressWarnings("unchecked")
		List<Questions> answeredQuestions = (List<Questions>) request
				.getSession().getAttribute("answeredQuestions");
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		if (isLastQuestion == 0) {

			if (answeredQuestions == null) {
				answeredQuestions = new ArrayList<Questions>();
				answeredQuestions.add(question);
				request.getSession().setAttribute("answeredQuestions",
						answeredQuestions);
			} else {
				answeredQuestions.add(question);
				request.getSession().setAttribute("answeredQuestions",
						answeredQuestions);
			}
			@SuppressWarnings("unchecked")
			List<Questions> questionsToAttempt = (List<Questions>) request
					.getSession().getAttribute("questionsBeingAnswered");
			Quiz quizToAttempt = (Quiz) request.getSession().getAttribute(
					"quizBeingAnswered");
			// get the current question object and send it to the jsp
			Questions currentQuestion = questionsToAttempt.get(currentCount);
			model.addAttribute("question", currentQuestion);
			model.addAttribute("quiz", quizToAttempt);
			model.addAttribute("currentCount", currentCount + 1);
			if (currentCount + 1 == questionsToAttempt.size()) {
				model.addAttribute("isLastQuestion", 1);
			} else {
				model.addAttribute("isLastQuestion", 0);
			}
			return "question";

		} else {
			// get the list of answered questions from session and pass it to
			// service layer
			answeredQuestions.add(question);
			Quiz quizToAttempt = (Quiz) request.getSession().getAttribute(
					"quizBeingAnswered");
			Result<ScoreCard> newScore = scoreCardSer.attemptQuiz(
					quizToAttempt, currentUser, answeredQuestions);
			ScoreCard currentScoreCard = newScore.getEntity();
			if (newScore.getResultCode() == 0) {
				model.addAttribute("myScore", currentScoreCard.getScore());
				model.addAttribute("quiz", quizToAttempt);
				model.addAttribute("highestScore", scoreCardSer
						.findHighestScore(quizToAttempt.getScores()));
				return "scoreForAttemptedQuiz";
			} else if (newScore.getResultCode() == -2) {
				return "defaultError";
			}
			// return score card view
			return "scoreForAttemptedQuiz";
		}

	}

	/**
	 * Get the view to attempt a quiz
	 * 
	 * @param quizId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quizzes/attempt/{quizId}", method = RequestMethod.GET)
	public String attempQuiz(@PathVariable("quizId") int quizId,
			HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		Quiz attemptingQuiz = quizSer.getQuiz(quizId);
		model.addAttribute("quiz", attemptingQuiz);
		return "quizAttempt";
	}

	/**
	 * Create a quiz get the view to create new
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quiz/create", method = RequestMethod.GET)
	public String createQuiz(Locale locale, Model model,
			HttpServletRequest request) {
		Quiz newQ = new Quiz();
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}

		List<Category> categoryList = categorySer.getAllCategories();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("quiz", newQ);

		model.addAttribute("levelList", quizSer.getLevelList());
		return "createQuiz";
	}

	/**
	 * Create a new quiz post method
	 * 
	 * @param postQuiz
	 * @return
	 */
	@RequestMapping(value = "/quiz/create", method = RequestMethod.POST)
	public String createQuiz(@RequestParam("quizName") String quizName,
			@RequestParam("category") Integer categoryId,
			@RequestParam("level") Integer level, Model model,
			HttpServletRequest request) {
		Quiz postQuiz = new Quiz();
		if (level == null) {
			level = 1;
		}
		postQuiz.setLevel(level);
		postQuiz.setName(quizName);
		Result<Quiz> res = quizSer.add(postQuiz, categoryId);
		int QId=res.getEntity().getId();
		request.getSession().setAttribute("quizId",QId );
		Questions emptyQ = new Questions();

		model.addAttribute("question", emptyQ);
		request.getSession().setAttribute("isLastQuestion", 0);
		return "questions";
	}

	/**
	 * Create a new quiz post method
	 * 
	 * @param postQuiz
	 * @return
	 */
	@RequestMapping(value = "/quiz/addQuestions", method = RequestMethod.POST)
	
	public String addQuestions(@ModelAttribute("question") Questions question,
			Model model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Questions> questions = (List<Questions>) request.getSession()
				.getAttribute("addedQuestions");
		int isLastQuestion = (Integer) request.getSession().getAttribute(
				"isLastQuestion");
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		if (isLastQuestion == 0) {
			if (questions == null) {
				questions = new ArrayList<Questions>();
				questions.add(question);
				request.getSession().setAttribute("addedQuestions", questions);
			} else {
				questions.add(question);
				request.getSession().setAttribute("addedQuestions", questions);
			}
			Questions emptyQ = new Questions();
			model.addAttribute("question", emptyQ);

			if (questions.size() <= 5) {
				request.getSession().setAttribute("isLastQuestion", 0);
			} else {
				request.getSession().setAttribute("isLastQuestion", 1);
			}

			return "questions";
		} else {
			int quizID = (Integer) request.getSession().getAttribute("quizId");
			quizSer.addQuestions(quizID, questions);
		}
		return "redirect:/quiz/search";
	}

	/**
	 * Get a list of quizzes and search on the basis of various factors like
	 * name, category, difficulty level etc
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quiz/search", method = RequestMethod.GET)
	public String searchQuiz(Locale locale, Model model,
			HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (currentUser == null) {
			return "redirect:/";
		}
		List<Quiz> quizList = quizSer.getList();
		model.addAttribute("quizList", quizList);
		return "listQuizzes";
	}

	/**
	 * Get the application landing page
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Login modelLogin = new Login();
		List<ScoreCard> topScorers = scoreCardSer.getTopScorers();
		HashMap<String, Integer> categoryDistribution = quizSer
				.getCategoryWiseList();
		modelLogin.setUsername("");
		modelLogin.setPassword("");
		model.addAttribute("loginModel", modelLogin);
		model.addAttribute("topScores", topScorers);
		model.addAttribute("categoryDistribution", categoryDistribution);
		return "dashboard";
	}

	/**
	 * Get the register page
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		User newUser = new User();
		model.addAttribute("newUser", newUser);
		return "register";
	}

	/**
	 * Get the register page
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User newUser, Model model) {

		// if -1 duplicate email address
		Result<User> res = userSer.add(newUser);
		if (res == null) {
			model.addAttribute("message",
					"Server side error. Please refresh the page and try again.");
			model.addAttribute("newUser", newUser);
			return "errorInRegistration";
		} else if (res.getResultCode() == -1) {
			// duplicate user exists for this email address
			model.addAttribute("message",
					"Please choose a unique email address.");
			model.addAttribute("newUser", newUser);
			return "errorInRegistration";
		}
		// -2 hibernate exception
		else if (res.getResultCode() == -2) {
			model.addAttribute("message",
					"Some error occured on the server side. Please try again.");
			model.addAttribute("newUser", newUser);
			return "errorInRegistration";
		} else if (res.getResultCode() == -0) {

			Login modelLogin = new Login();
			List<ScoreCard> topScorers = scoreCardSer.getTopScorers();
			modelLogin.setUsername("");
			modelLogin.setPassword("");
			model.addAttribute("loginModel", modelLogin);
			model.addAttribute("topScores", topScorers);
			return "dashboard";
		}
		return null;

	}

	/**
	 * Logout from the system. Invalidate the session object and redirect to
	 * login page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}

}
