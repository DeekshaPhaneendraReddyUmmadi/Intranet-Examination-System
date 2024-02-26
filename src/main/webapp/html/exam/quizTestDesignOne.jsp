<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="in.ac.ksrmce.config.admin_config.Authenticate" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsEntity" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsDao" %>	
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>




<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>quiz</title>
    <link rel="stylesheet" href="../../css/exam/quizTestDesignOne.css" />
   </head>
  <body>
    <div class="main-container">
      <div class="head">
        <p>KGCET Examination System</p>
        <div class="left">
          <a class="instruction-btn" href="#">P</a>
          <p style="font-size: 14px">Question Paper</p>
        </div>
        <div class="right">
          <a class="instruction-btn i" href="#">I</a>
          <p style="font-size: 14px">View Instruction</p>
        </div>
      </div>
      <div class="top">
        <div class="left">
          <div class="information">KGCET - ENGINEERING</div>
          <div class="information timer-sec">
            <div class="left">
              Section
            </div>
            <div class="right" >
                Time Left : <span id="timer">180</span> minutes
            </div>
          </div>
          <div class="information subject">
            <div>
              <div> <a href="#">Maths</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
              <div> <a href="#">Physics</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
              <div style="border-right: 3px solid gray"> <a href="#">Chemistry</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
            </div>
        </div>
          <div class="question-type information">
            Question Type : Multiple Choice Questions
          </div>
        </div>
        <div class="right">
          <img src="myphoto.jpg" alt="student image" />
          <div>Ummadi Deeksha Phaneendra Reddy</div>
        </div>
      </div>
      <div class="middle">
        <div class="left">
          <p style="padding-top: 20px"></p>
          
<!-- ________________________________________________________________________________________________________________________ -->          




<%


Connection con=QuestionsDao.getConnection();
PreparedStatement ps=con.prepareStatement("SELECT * FROM questions ORDER BY RAND() LIMIT "+ QuestionsDao.count());
ResultSet result=ps.executeQuery();

if (result.next()) {
    int questionNumber = 1;
    
    String imagePath = "../../images/questions/"; 

    // Retrieve the image filenames from the database
    String question = imagePath + result.getString("question");
    String option1ImagePath = imagePath + result.getString("option_one");
    String option2ImagePath = imagePath + result.getString("option_two");
    String option3ImagePath = imagePath + result.getString("option_three");
    String option4ImagePath = imagePath + result.getString("option_four");
    do {
%>
        <fieldset class="question-div" id="question<%= questionNumber %>" style="display: <%= (questionNumber == 1) ? "block" : "none" %>">
            <legend>Question <%= questionNumber %></legend>
            <img style="width:50%" src="<%= question %>" alt="Question <%= questionNumber %>"><br>
            <label>
                <input type="radio" name="option" value="1">
                <img style="width:11%" src="<%= option1ImagePath %>" alt="Option 1">
            </label><br>
            <label>
                <input type="radio" name="option" value="2">
                <img style="width:11%" src="<%= option2ImagePath %>" alt="Option 2">
            </label><br>
            <label>
                <input type="radio" name="option" value="3">
                <img style="width:11%" src="<%= option3ImagePath %>" alt="Option 3">
            </label><br>
            <label>
                <input type="radio" name="option" value="4">
                <img style="width:11%" src="<%= option4ImagePath %>" alt="Option 4">
            </label>
        </fieldset>
<%
        questionNumber++;
    } while (result.next());

    // Show both buttons initially (it will be adjusted in the script)
%>
    <!-- <div class="navigation">
    	
        <button type="reset">Reset</button>
        <button type="button" class="save-next-button" onclick="saveAndNext()">Save & Next</button>
        <button type="button" class="submit-button" onclick="submitExam()">Submit Exam</button>
    </div> -->
<%
} else {
    out.println("No questions found.");
}
result.close();
%>






<!-- ________________________________________________________________________________________________________________________ -->          
          
        </div>

        <div class="right">
          <div class="top">

            <div style="display: flex;">
              <a href="#" style="text-decoration: none;">
                <div style="position: relative; display: inline-block; width: 50px; margin-bottom: -15px;">
                  <div style="border-bottom: 20px solid #53cb35; border-left: 15px solid transparent; border-right: 15px solid transparent;"></div>
                  <div style="height: 25px; background-color: #53cb35;"></div>
                  <div style="font-size: 24px; color: white; text-align: center; transform: translate(-0%, -130%);">0</div>
                </div>
              </a><p>Answered</p>
              <a href="#" style="text-decoration: none;">
                <div style="margin-top: 50px; position: relative; display: inline-block; margin-top: 60px;">
                  <div style="color: white; text-align: center; position: absolute; top: -50px; width: 50px; height: 22px; background-color: #ff6347;"></div>
                  <div style="margin-top: -28px; border-top: 20px solid #ff6347; border-left: 15px solid transparent; border-right: 15px solid transparent; height: 0; width: 21px;"></div>
                  <div style="font-size: 24px; color: white; text-align: center; transform: translate(-0%, -130%);">0</div>
                </div>
              </a><p>Not Answered</p>
            </div>

            <div style="display: flex;">
              <a  style="text-decoration: none;">
                <div  style="display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 4px; background-color: rgb(250, 248, 248);">
                  <div  style="text-align: center; font-size: 24px;">
                    <p class="initial">0</p>
                  </div>
                </div>
              </a><p>Not Visited</p>
              <a class="anchor" style="text-decoration: none;">
                <div class="container" style="display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px;">
                    <p class="initial" style="color: white;">0</p>
                  </div>
                </div>
              </a>
              <div style="width: 80px;">Marked for Review</div>
            </div>

            <div style="display: flex;">
              <a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>
              <p style="color: black;">Answered & Marked for Review (will be considered for evaluation)</p>
            </div>

            <div class="middle">Subject</div>
            <div style="background-color: #bad3ed;">
              choose a question
            </div>
            </div>

          <div class="bottom " style="display: flex;">
            
            
            <!-- <div id="shapeContainer"></div> -->

           <div class="coloring-btn" style="background-color: #bad3ed ; margin-top: -20px;">
           
           		<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>
              
              <a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a><a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>

				<a class="anchor" href="#" style="text-decoration: none; display: inline-block; position: relative; margin:10px">
                <div class="container" style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div class="initial" style="text-align: center; font-size: 24px; color: white;">
                    <p class="initial">0</p>
                  </div>
                  <img src="text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>
              
              
           </div>
  
          </div>

          

        </div>
      </div>
      <div class="bottom">
      	<div class="navigation left">
            <div class="btm-btn">
                <button type="button" class="btm-btn-left" >Mark for Review & Next</button>
                <button type="reset" class="btm-btn-left">Clear Response</button>
            </div>
            <button class="btm-btn-right" type="button" class="save-next-button btm-btn-right" onclick="saveAndNext()">Save & Next</button>
        </div>
    <!-- <div class="left">
        <a href="#" class="next-btn">Mark for Review & Next</a>
    </div> -->
        <div class="navigation right ">
            <!-- <button class="btm-btn-right" type="button" class="submit-button" onclick="submitExam()">Submit Exam</button> -->
            <a class="btm-btn-right" href="#"> SUBMIT </a>
        </div>
      </div>
    </div>

    <script src="../../js/exam/quiz.js"></script>
   
  </body>
</html>
