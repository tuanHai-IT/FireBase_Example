package vn.edu.ueh.thanhdnh.firebase_example;

public class Article {
  private String title;
  private String description;

  public Article() {
  }

  public Article(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Article{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
