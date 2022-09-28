package apu.gym.centre.management.system.Objects;
public class Feedback {
    private int Stars;
    private String Comment;

    public Feedback(int Stars, String Comment) {
        this.Stars = Stars;
        this.Comment = Comment;
    }
    
    public Feedback(String RawFeedback){
        String[] components = RawFeedback.split("\\|Stars-Seperator-Comment\\|");
        this.Stars = Integer.parseInt(components[0]);
        this.Comment = components[1];
    }

    public int getStars() {
        return Stars;
    }

    public String getComment() {
        return Comment;
    }
    
    public String toString(){
        return Integer.toString(Stars)+"|Stars-Seperator-Comment|"+Comment;
    }
    public String toDisplayString(){
        if(Comment.equals("NOT YET RATED")){
            return "Not Yet Rated";
        }
        return Integer.toString(Stars)+"Stars, "+Comment;
    }
}
