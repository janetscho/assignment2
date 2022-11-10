package Pattern;

import Objects.User;

public class TweetVisitor implements Visitor {
    @Override
    public int visit(User user) {
        return user.getTweets().size();
    }
    
}
