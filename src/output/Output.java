package output;

import actions.ActionVisitor;

import actions.ChangePage;

import actions.Login;

import actions.Register;

import actions.Visitor;

import actions.Search;

import actions.Filter;

import actions.BuyPremAcc;

import actions.BuyTokens;

import actions.Purchase;

import actions.Watch;

import actions.Rate;

import actions.Like;

import constants.Feature;

import constants.Page;

import constants.Type;

import input.ActionInput;

import input.Input;

import input.MovieInput;

import input.UserInput;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;

import java.util.List;

public final class Output {
    private static volatile Output instance = null;
    private final List<OutputMessage> output;
    private final Input input;
    private String currentPage;
    private UserExtended currentUser;
    private List<MovieExtended> currentMoviesList;
    private final ArrayList<UserExtended> users;
    private final ArrayList<MovieExtended> movies;
    private final HashMap<MovieExtended, ArrayList<Integer>> moviesRatings;

    /**
     * Private constructor for singleton imposes the use of getInstance()
     */
    private Output(final Input input) {
        output = Collections.synchronizedList(new ArrayList<>());
        this.input = input;
        currentPage = Page.UNAUTHENTICATED.getPage();
        currentUser = null;
        currentMoviesList = Collections.synchronizedList(new ArrayList<>());
        users = new ArrayList<>();
        movies = new ArrayList<>();

        for (UserInput user : input.getUsers()) {
            users.add(new UserExtended(user));
        }

        for (MovieInput movie : input.getMovies()) {
            movies.add(new MovieExtended(movie));
        }

        moviesRatings = new HashMap<>();

        for (MovieExtended movie : movies) {
            moviesRatings.put(movie, new ArrayList<>());
        }
    }

    /**
     * Synchronized method for singleton
     *
     * @return the instance of the singleton pattern class
     */
    public static Output getInstance(final Input input) {
        if (instance == null) {
            synchronized (Output.class) {
                if (instance == null) {
                    instance = new Output(input);
                }
            }
        }

        return instance;
    }

    public static Output getInstance() {
        return instance;
    }

    /**
     * Resets the singleton instance for the next test case.
     */
    public void setInstance() {
        instance = null;
    }

    public List<OutputMessage> getOutput() {
        return output;
    }

    public Input getInput() {
        return input;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    public UserExtended getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final UserExtended currentUser) {
        this.currentUser = currentUser;
    }

    public List<MovieExtended> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<MovieExtended> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public HashMap<MovieExtended, ArrayList<Integer>> getMoviesRatings() {
        return moviesRatings;
    }

    public ArrayList<UserExtended> getUsers() {
        return users;
    }

    public ArrayList<MovieExtended> getMovies() {
        return movies;
    }


    /**
     * Implements visitor pattern for the actions.
     */
    public void runActions() {
        Visitor actionVisitor;

        for (ActionInput action : input.getActions()) {
            actionVisitor = new ActionVisitor();

            if (Type.CHANGE.getType().equals(action.getType())) {
                ActionExtended changePage = new ChangePage(action);
                changePage.accept(actionVisitor);
            } else if (Feature.LOGIN.getFeature().equals(action.getFeature())) {
                ActionExtended login = new Login(action);
                login.accept(actionVisitor);
            } else if (Feature.REG.getFeature().equals(action.getFeature())) {
                ActionExtended register = new Register(action);
                register.accept(actionVisitor);
            } else if (Feature.SEARCH.getFeature().equals(action.getFeature())) {
                ActionExtended search = new Search(action);
                search.accept(actionVisitor);
            } else if (Feature.FILTER.getFeature().equals(action.getFeature())) {
                ActionExtended filter = new Filter(action);
                filter.accept(actionVisitor);
            } else if (Feature.BUY_TOK.getFeature().equals(action.getFeature())) {
                ActionExtended buyTokens = new BuyTokens(action);
                buyTokens.accept(actionVisitor);
            } else if (Feature.BUY_PREM_ACC.getFeature().equals(action.getFeature())) {
                ActionExtended buyPremAcc = new BuyPremAcc(action);
                buyPremAcc.accept(actionVisitor);
            } else if (Feature.PURCHASE.getFeature().equals(action.getFeature())) {
                ActionExtended purchase = new Purchase(action);
                purchase.accept(actionVisitor);
            } else if (Feature.WATCH.getFeature().equals(action.getFeature())) {
                ActionExtended watch = new Watch(action);
                watch.accept(actionVisitor);
            } else if (Feature.LIKE.getFeature().equals(action.getFeature())) {
                ActionExtended like = new Like(action);
                like.accept(actionVisitor);
            } else {
                ActionExtended rate = new Rate(action);
                rate.accept(actionVisitor);
            }

            if (actionVisitor.getOutputMessage() != null) {
                output.add(actionVisitor.getOutputMessage());
            }
        }
    }
}
