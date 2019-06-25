package korzoApp.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import korzoApp.model.Film;



public class PageFilmDTO {
	
	
	private int size;
	
	private int index;
	
	private List<FilmDTO> content = new ArrayList<>();
	
	private boolean hasNext;
	
	private boolean hasPrevious;
	
	private long numberOfFilms;
	
	private int totalPages;

	public PageFilmDTO() {
		super();
	}
	
	public PageFilmDTO(Page<Film> page) {
		this.size = page.getSize();
		this.index = page.getNumber();
		this.content = page.getContent().stream()
						.map(FilmDTO::new)
						.collect(Collectors.toList());
		this.hasNext = page.hasNext();
		this.hasPrevious = page.hasPrevious();
		this.numberOfFilms = page.getTotalElements();
		this.totalPages = page.getTotalPages();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<FilmDTO> getContent() {
		return content;
	}

	public void setContent(List<FilmDTO> content) {
		this.content = content;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public long getNumberOfFilms() {
		return numberOfFilms;
	}

	public void setNumberOfFilms(long numberOfFilms) {
		this.numberOfFilms = numberOfFilms;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	



}
