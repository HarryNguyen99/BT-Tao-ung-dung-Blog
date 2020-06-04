package service.ipml;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import repository.BlogRepository;
import service.BlogService;

import java.util.List;

@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(Blog blog) {
     blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);
    }
}
