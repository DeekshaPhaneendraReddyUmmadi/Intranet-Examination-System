<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0" />
    <title>Instructions</title>
    <style>
      .answered-link {
        display: inline-block;
        position: relative;
        width: 50px;
        height: 45px;
        background-image: url("../../images/exam/A.png");
        background-size: cover;
        background-position: center;
        text-align: center;
        line-height: 43px;
        color: white;
        font-size: 24px;
        text-decoration: none;
        margin: 10.5px;
      }

      .notanswered-link {
        display: inline-block;
        position: relative;
        width: 50px;
        height: 45px; 
        background-image: url("../../images/exam/notA.png");
        background-size: cover;
        background-position: center;
        text-align: center;
        line-height: 43px; 
        color: white;
        font-size: 24px;
        text-decoration: none;
        margin: 10.5px;
      }
      .notVisited-link {
        display: inline-block;
        position: relative;
        width: 50px;
        height: 50px;
        background-image: url("../../images/exam/NVisited.png");
        background-size: cover;
        background-position: center;
        text-align: center;
        line-height: 45px;
        color: black;
        font-size: 24px;
        text-decoration: none;
        margin: 10.5px;
      }
      .markedforreview-link {
        display: inline-block;
        position: relative;
        width: 50px;
        height: 50px;
        background-image: url("../../images/exam/MFR.png");
        background-size: cover;
        background-position: center;
        text-align: center;
        line-height: 45px;
        color: white;
        font-size: 24px;
        text-decoration: none;
        margin: 10.5px;
      }
      .answeredandmarkedforreview-link {
        display: inline-block;
        position: relative;
        width: 50px;
        height: 50px; 
        background-image: url("../../images/exam/AMFR.png"); 
        background-size: cover;
        background-position: center;
        text-align: center;
        line-height: 45px;
        color: white;
        font-size: 24px;
        text-decoration: none;
        margin: 10.5px;
      }
    </style>
  </head>
  <body style="margin: auto ;">
    <div style="display: flex">
      <div
        class="left"
        style="
          width: 80%;
          border-right: 2px solid black;
          font-size: 18px;
          height: 735px;
        "
      >
        <div
          style="
            height: 50px;
            background-color: #0b2646;
            color: white;
            font-size: 28px;
            padding-top: 13px;
            padding-left: 15px;
          "
        >
          Instructions
        </div>
        
        <div style="font-size: 16px ; overflow-y: auto; height:82%; border-bottom: 2px solid gray;padding-left: 20px;">
            <p style="text-align: center; font-size:24px;">Please read the instructions carefully</p>
            <p style="text-decoration: underline;font-size:20px;">General Instructions:</p>
          <ol>
            <li>Total duration of examination is 180 minutes.</li>
            <li>
              The clock will be set the server. The countdown is the top right
              corner of screen will display the remaining time available for you
              to complete the examination. When the timer reaches zero, the
              examination will end by itself. You will not be required to end or
              submit your examination.
            </li>
            <li>
              The Question Palette displayed on the right side of the screen
              will show the status of each question using one of the following
              symbols:
              <br />
              <a href="" class="notVisited-link">1</a>  You have not visited the question yet.
              <br />
              <a href="" class="notanswered-link">2</a> You have not answered the question.
              <br />
              <a href="" class="answered-link">3</a> You have answered the question.
              <br />
              <a href="" class="markedforreview-link">4</a> You have NOT answered the question,but have marked the question for review. This will NOT be considered for evaluation.
              <br />
              <a href="" class="answeredandmarkedforreview-link">5</a> The question(s) "Ansered and Marked for Review" will be considered for evaluation. <br/>
              The Marked for Review status for a questions simply indicates that you would like to look at that question again.
            </li>
          </ol>
          <p style="text-decoration: underline ; font-size:20px;">Navigating to a Question:</p>
          <ol start="4">
                <li>
                    To answer a question, do the following:
                    <ol type="a">
                        <li>
                            Click on the questions number in the Question Palette at the right of your screen to go that numbered question directly. Note that using this option does NOT save your answer to the current question.
                        </li>
                        <li>Click on Save & Next to save your answer for the current quetsion and then go to the next question.</li>
                        <li>Click on Mark for Review & Next to save your answer for the current question, mark it for review, and then go to the next question.</li>
                    </ol>
                </li>
          </ol>
          <p style="text-decoration: underline; font-size:20px;">Answering a Question:</p>
          <ol start="5">
                <li>
                    Procedure for answering a multiple choice type question:
                    <ol type="a">
                        <li> To select your answer, click on the button of one of the options.</li>
                        <li>To deselect your chosen answer, click on the button of the chosen option again or click on the Clear Response button.</li>
                        <li>To change your chosen answer, click on the button of another option.</li>
                        <li>To save your answer, you MUST click on the Save & Next button.</li>
                        <li>To mark the question for review, click on the Mark for Review & Next button.</li>
                    </ol>
                </li>
                <li>To change your answer to a question that has already been answered, first select that question for answering and then follow the procedure for answering that type of question.</li>
          </ol>
          <p style="text-decoration: underline; font-size:20px;">Navigating through sections:</p>
          <ol start="7">
            <li> Sections in this question paper are displayed on the top bar of the screen. Questions in a section can be viewed by clicking on the section name. The section you are currently viewing is highlighted.</li>
            <li>After clicking the Save & Next button on the last question for a section, you will automatically be taken to the first question of the next section.</li>
            <li></li> You can shuffle between sections and questions anytime during the examination as per your convenience only during the time stipulated.</li>
            <li>Candidate can view the corresponding section summary as part of the legend that appears in every section above the question palette.</li>
          </ol>
        </div>
        <div style="border-top: 2px soild black; height: 50px;"><a href="importantInstruction.jsp" style="color:black;margin-top: 10px; margin-left : 87% ;display: flex; justify-content: center; width: 100px ; height:30px; text-decoration: none; border: 2px solid black; border-radius: 3px; padding-top: 7px;">Next ></a></div>
      </div>
      
			<% 
			String name = (String)session.getAttribute("userName");
			String photo = (String)session.getAttribute("photo");
			
			%>
      <div class="right" style="width: 25%">
            <img src="<%= photo %>" alt="student image" style="margin-top: 24%; margin-left: 26% ; width: 170px; ">
            <p style="text-align: center;"><%= name %></p>
        </div>
    </div>
  </body>
</html>
