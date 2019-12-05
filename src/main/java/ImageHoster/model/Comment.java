package ImageHoster.model;


import javax.persistence.*;
import java.util.Date;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'. Hence the table named 'comment' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "comment_Image")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer commentId;

    // Text is a Postgres specific column type that allows you to save
    // text based data that will be longer than 256 characters
    // this is a base64 encoded version of the image
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "createdDate")
    private Date date;
/*
    //The 'users' table is mapped to 'user_profile' table with One:One mapping
    //cascade = CascadeType.ALL specifies that if a record in 'user_profile' table is deleted or updated, then all the records in 'users' table associated to that particular record in 'user_profile' table will be deleted or updated  first and then the record in the 'user_profile' table will be deleted or updated
    //FetchType is EAGER
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'users' table referring the primary key in 'user_profile' table will be 'profile_id'
    @JoinColumn(name = "profile_id")
    private UserProfile profile;
*/
    //The 'comment' table is mapped to 'users' table with Many:One mapping
    //One comment can have only one user (owner) but one user can have multiple comment
    //cascade = CascadeType.ALL specifies that if a record in 'users' table is deleted or updated, then all the records in 'comment' table associated to that particular record in 'users' table will be deleted or updated  first and then the record in the 'users' table will be deleted or updated
    //FetchType is EAGER
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'users' table will be 'user'
    @JoinColumn(name = "userId")
    private User users_id;


    //The 'comment' table is mapped to 'images' table with Many:One mapping
    //One comment can have only one image associated with it but one image can have multiple comment
    //cascade = CascadeType.ALL specifies that if a record in 'images' table is deleted or updated, then all the records in 'comment' table associated to that particular record in 'images' table will be deleted or updated  first and then the record in the 'images' table will be deleted or updated
    //FetchType is EAGER
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'images' table will be 'Image'
    @JoinColumn(name = "Image")
    private Image images_id;


    public Comment() {
    }

    public Comment(int id, String text, Date date) {
        this.commentId = id;
        this.text = text;
        this.date = date;
    }


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer id) {
        this.commentId = id;
    }

    public Date getCreatedDate() {
        return date;
    }

    public void setCreatedDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return users_id;
    }

    public void setUser(User user) {
        this.users_id = user;
    }

    public Image getImages() {
        return images_id;
    }

    public void setImages(Image images) {
        this.images_id = images;
    }

}
