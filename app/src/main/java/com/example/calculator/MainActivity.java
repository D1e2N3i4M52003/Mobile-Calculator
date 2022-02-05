package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import android.text.SpannableStringBuilder;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we get the EditedText elemend and give it to a variable that we will call
        display=findViewById(R.id.input);
        //doesn't show possible endings of the text that is entered
        display.setShowSoftInputOnFocus(false);

        //we set up a listener to the click event on the display
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if we just entered the text field, the display is cleared
                if(getString(R.string.display).equals(display.getText().toString())){
                    //the display is cleared
                    display.setText("");
                }
            }
        });
    }

    //finction that updates the display
    private void updateText(String stringToAdd){
        //we get the text that was in the display before the function is called
        String oldSTR = display.getText().toString();
        //we get the position of the cursor
        int cursorPos= display.getSelectionStart();
        //we make a substring containing the part of the text before the cursor
        String leftStr = oldSTR.substring(0, cursorPos);
        //we make a substring containing the part of the text after the cursor
        String righttStr = oldSTR.substring(cursorPos);
        //if there was no text before the function is called we add just the stringToAdd
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(stringToAdd);
            //we update the cursor position
            display.setSelection(cursorPos+1);
        }
        //if there was text before the function is called
        else{
            //we attach the left string + stringToAdd + the right string
            display.setText(String.format("%s%s%s", leftStr, stringToAdd,righttStr));
            display.setSelection(cursorPos+1);
        }
    }

    //add 0 to the display
    public void zeroBTN(View view){
        updateText("0");
    }

    //add 1 to the display
    public void oneBTN(View view){
        updateText("1");
    }

    //add 2 to the display
    public void twoTN(View view){
        updateText("2");
    }

    //add 3 to the display
    public void threeBTN(View view){
        updateText("3");
    }

    //add 4 to the display
    public void fourBTN(View view){
        updateText("4");
    }

    //add 5 to the display
    public void fiveBTN(View view){
        updateText("5");
    }

    //add 6 to the display
    public void sixBTN(View view){
        updateText("6");
    }

    //add 7 to the display
    public void sevenBTN(View view){
        updateText("7");
    }

    //add 8 to the display
    public void eightBTN(View view){
        updateText("8");
    }

    //add 9 to the display
    public void nineBTN(View view){
        updateText("9");
    }

    //add ÷ to the display
    public void divideBTN(View view){
        updateText("÷");
    }

    //add × to the display
    public void multiplyBTN(View view){
        updateText("×");
    }

    //add - to the display
    public void subtractBTN(View view){
        updateText("-");
    }

    public void addBTN(View view){
        updateText("+");
    }

    //clear the display
    public void clearBTN(View view){
        display.setText("");
    }

    //add parenthesis to the display
    public void parenthesisBTN(View view){
        //get the cursor position
        int cursorPos=display.getSelectionStart();

        //keep track of all the opened and closed parenthesis
        int openPar=0;
        int closedPar=0;
        //het the lenght of the text from the display
        int textLen=display.getText().length();

        //loop that counts the opened and closed parenthesis
        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar++;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar++;
            }
        }

        //if the number of opened parenthesis is equal to the closed ones or the character before the cursor is an opened parenthesis
        if(openPar==closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            //we add '('
            updateText("(");
            //move the cursor with one on right
            display.setSelection(cursorPos+1);
        }
        //if the number of opened parenthesis is grater to the closed ones or the character before the cursor is not an opened parenthesis
        else if(openPar>closedPar || !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            //we add ')'
            updateText(")");
            display.setSelection(cursorPos+1);
        }
    }

    //add ^ to the display
    public void exponentBTN(View view){
        updateText("^");
    }

    //clear one character
    public void backspaceBTN(View view){
        int cursorPos=display.getSelectionStart();
        int textLen= display.getText().length();

        //if the cursor position isn't 0 and the lenght of the text isn't 0
        if(cursorPos != 0 && textLen!=0){
            //create a SpannableStringBuilder from the text from the display
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            //replace the character in the cursor position with empty string
            selection.replace(cursorPos-1, cursorPos, "");
            //set the display to the changed text
            display.setText(selection);
            //we move the cursor one space left
            display.setSelection(cursorPos-1);
        }
    }

    public void plusMinusBTN(View view){
        updateText("0");
    }

    //calculate the expression
    public void equalsBTN(View view){
        //get the text from the display
        String userExp = display.getText().toString();

        //replace the alt charachters with normal ones because the library we use works only with normal ones
        userExp=userExp.replaceAll("÷", "/");
        userExp=userExp.replaceAll("×", "*");

        //create an element from the class expression with the value of the display
        Expression exp = new Expression(userExp);
        //we calculate the expression and then add it to a new string
        String result = String.valueOf(exp.calculate());

        //put the result in the display
        display.setText(result);
        //update the cursor position at the end
        display.setSelection(result.length());
    }

    //add . to the display
    public void pointBTN(View view){
        updateText(".");
    }
}
