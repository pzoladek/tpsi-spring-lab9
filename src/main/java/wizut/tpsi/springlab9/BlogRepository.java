package wizut.tpsi.springlab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private DataSource dataSource;

    public List<BlogPost> getAllPosts() throws SQLException {
        List<BlogPost> posts = new ArrayList<>();

        String sql = "select * from blog_post";

        try(Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");

                BlogPost post = new BlogPost(id, title, content);
                posts.add(post);
            }
        }

        return posts;
    }

    public void createPost(BlogPost post) throws SQLException {
        String sql = "insert into blog_post(title, content) values(?, ?)";

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());

            ps.executeUpdate();
        }
    }

    public void deletePost(long id) throws SQLException {
        String sql = "delete from blog_post where id = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        }
    }
}
