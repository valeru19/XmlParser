public class Review {

    private String user;
    private int rating;
    private String comment;

    public Review() {}

    public Review(String user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "user='" + user + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

}
