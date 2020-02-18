/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;
import entities.Joke;

/**
 *
 * @author oscar
 */
public class JokeDTO {
    
    private String punchLine;
    private String category;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public JokeDTO (Joke joke){
        this.punchLine=joke.getPunchLine();
        this.category=joke.getCategory();
        this.id=joke.getId();
    }

    public String getPunchLine() {
        return punchLine;
    }

    public void setPunchLine(String punchLine) {
        this.punchLine = punchLine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
