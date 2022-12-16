package actions;

import input.ActionInput;

import output.ActionExtended;

public final class Search extends ActionExtended {
    public Search(final ActionInput action) {
        super(action);
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }
}
