package actions;

import output.OutputMessage;

public interface Visitor {
    /**
     * @return error status, current movie list and current user
     */
    OutputMessage getOutputMessage();

    /**
     * Return the logged-in user to the previous page he was on
     */
    void visit(Back back);

    /**
     * Changes the current page
     */
    void visit(ChangePage changePage);

    /**
     * Logins into an existing account and enters the authenticated homepage
     */
    void visit(Login login);

    /**
     * Registers a non-existing account into the user database
     */
    void visit(Register register);

    /**
     * Searches for movies that start with the given string
     */
    void visit(Search search);

    /**
     * Sorts the movies by rating and duration
     */
    void visit(Filter addFavorite);

    /**
     * User upgrades his account to premium
     */
    void visit(BuyPremAcc buyPremAcc);

    /**
     * User buys virtual tokens
     */
    void visit(BuyTokens buyTokens);

    /**
     * User purchases the selected movie
     */
    void visit(Purchase purchase);

    /**
     * User watches the selected movie
     */
    void visit(Watch watch);

    /**
     * User likes the selected movie
     */
    void visit(Like like);

    /**
     * User rates the selected movie
     */
    void visit(Rate rate);

    /**
     * User subscribes to the selected movie genre
     */
    void visit(Subscribe subscribe);

    /**
     * Movie added to the database
     */
    void visit(DatabaseAdd databaseAdd);

    /**
     * Movie removed from the database
     */
    void visit(DatabaseDelete databaseDelete);
}
