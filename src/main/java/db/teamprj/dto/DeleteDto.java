package db.teamprj.dto;

import java.util.ArrayList;

public class DeleteDto {

    private ArrayList<String> check;

    public DeleteDto(ArrayList<String> check) {
        this.check = check;
    }

    public ArrayList<String> getCheck() {
        return check;
    }

    public void setCheck(ArrayList<String> check) {
        this.check = check;
    }
}
