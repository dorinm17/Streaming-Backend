package constants;

public enum Page {
    UNAUTHENTICATED("unauthenticated"),
    AUTHENTICATED("authenticated"),
    LOGIN("login"),
    REG("register"),
    MOVIES("movies"),
    DETAILS("see details"),
    UPGRADES("upgrades"),
    LOGOUT("logout");

    private final String page;

    Page(final String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
