package korzoApp.web.dto;

import java.util.List;

public class AddFilmDTO {
	
	private String title;
	
	private int year;
	
	private boolean domestic;
	
	private String storage;
	
	private List<Integer> genreIds;

	public AddFilmDTO() {
		super();
	}

	public AddFilmDTO(String title, int year, boolean domestic, String storage, List<Integer> genreIds) {
		super();
		this.title = title;
		this.year = year;
		this.domestic = domestic;
		this.storage = storage;
		this.genreIds = genreIds;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	
	
}
