package Pattern;

import Objects.User;

public class PositiveVisitor implements Visitor {

    @Override
    public int visit(User user) {
        int num = 0;
        String[] positive = { "happy", "friendly", "adorable", "sincere", "passion", "powerful",
            "courageous", "fantastic", "amazing", "honesty", "considerate", "enthusiam", "affection",
            "delightful", "beautiful", "admirable", "optimism", "lively", "excellent" };

        for(int i = 0; i < user.getTweets().size(); i++) {
            for(int j = 0; j < positive.length; j++) {
                if(user.getTweets().get(i).getMessage().contains(positive[j]))
                    num++;
            }
        }
        return num;

    }
    
}
