package com.dcinside;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DcinsideApplicationTests {

	@Autowired
	private GalleryRepository galleryRepository;

	@Autowired
	private PostRepository postRepository;


	@Test
	void galleryGenerationTest() {
		Gallery g1 = new Gallery();
		g1.setName("파워리프팅");
		g1.setCreateDate(LocalDateTime.now());
		this.galleryRepository.save(g1);
	}

	@Test
	void galleryGenerationCheckTest() {
		List<Gallery> all = this.galleryRepository.findAll();
		assertEquals(1, all.size());
	}

	@Test
	void galleryNameModifyTest(){
		Optional<Gallery> og = this.galleryRepository.findById(1);
		if (og.isPresent()) {
			Gallery gallery = og.get();
			gallery.setName("운동");
			this.galleryRepository.save(gallery);
		}
	}

	@Test
	void galleryDeleteTest(){
		galleryGenerationTest();
		List<Gallery> lg = this.galleryRepository.findAll();
		if(!lg.isEmpty()){
			int purposeId = lg.get(0).getId();
			Optional<Gallery> og = this.galleryRepository.findById(purposeId);
			if (og.isPresent()) {
				Gallery gallery = og.get();
				this.galleryRepository.delete(gallery);
			}
			Optional<Gallery> ogc = this.galleryRepository.findById(purposeId);
			assertTrue(ogc.isEmpty());
		}
	}

	@Test
	void test() {
		Optional<Gallery> og = this.galleryRepository.findById(3);
		if (og.isPresent()) {
			Gallery gallery = og.get();
			this.galleryRepository.delete(gallery);
		}
	}

	@Test
	void PostGenerationTest() {
		Optional<Gallery> og = this.galleryRepository.findById(5);
		assertTrue(og.isPresent());
		Gallery gallery = og.get();

		Post post = new Post();
		post.setSubject("벤치 느는 속도가 넘 느린데 어떻게 해야해?");
		post.setContent("벤치 60kg으로 훈련하고 있는데 왤케 힘드누");
		post.setGallery(gallery);
		post.setCreateDate(LocalDateTime.now());
		this.postRepository.save(post);
	}

	@Transactional
	@Test
	void GetPostListByGalleryTest(){
		Optional<Gallery> og = this.galleryRepository.findById(5);
		assertTrue(og.isPresent());
		Gallery gallery = og.get();
		List<Post> postList = gallery.getPostList();

		assertEquals(postList.get(0).getSubject(), "3대 운동 어떻게 시작하나요?");
	}
}
