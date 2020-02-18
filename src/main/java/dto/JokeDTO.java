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
    
    public String punchLine;
    public String category;
    
    public JokeDTO (Joke joke){
        this.punchLine=joke.getPunchLine();
        this.category=joke.getCategory();
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
