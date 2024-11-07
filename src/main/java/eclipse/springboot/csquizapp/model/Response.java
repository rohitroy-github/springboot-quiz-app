package eclipse.springboot.csquizapp.model;

public class Response {
	
	private Integer id;
	private String response;	

	// Getter for id
	public Integer getId() {
		return id;
	}

	// Setter for id
	public void setId(Integer id) {
		this.id = id;
	}

	// Getter for response
	public String getResponse() {
		return response;
	}

	// Setter for response
	public void setResponse(String response) {
		this.response = response;
	}

	// toString method
	@Override
	public String toString() {
		return "Response{" +
				"id=" + id +
				", response='" + response + '\'' +
				'}';
	}
}
