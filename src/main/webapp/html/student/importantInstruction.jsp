<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Important Instructions</title>
  <style>
    table {
      margin-left: 25px;
      margin-top: 30px;
      border-collapse: collapse;
      border: 2px solid rgb(140 140 140);
      font-family: sans-serif;
      font-size: 0.8rem;
      letter-spacing: 1px;
    }

    caption {
      caption-side: bottom;
      padding: 10px;
      font-weight: bold;
    }

    th,
    td {
      border: 1px solid rgb(160 160 160);
      padding: 8px 10px;
    }

    td:last-of-type {
      text-align: center;
    }

    tfoot th {
      text-align: right;
    }

    tbody tr {
      text-align: center;
    }

    tfoot td {
      font-weight: bold;
    }
  </style>
</head>

<body style="margin: auto">
  <div style="display: flex">
    <div class="left" style="
          width: 80%;
          border-right: 2px solid black;
          font-size: 18px;
          height: 735px;
        ">
      <div style="
            height: 50px;
            background-color: #0b2646;
            color: white;
            font-size: 28px;
            padding-top: 13px;
            padding-left: 15px;
          ">
        Other Important Instructions
      </div>
      <div style="font-size: 16px ; overflow-y: auto; height:63%; border-bottom: 2px solid gray;padding-left: 20px; ">
        <ol>
          <li>You will be given 180 minutes to attempt 200 questions.</li>
          <li>
            The Question Paper consists of objective type questions only.
          </li>
          <li>There will be no negative marks for wrong answers.</li>
          <li>
            To save your response, click on
            <span style="font-weight: bold">Save & Next</span> after answering
            each question. Otherwise, your response will not be saved.
          </li>
          <li>
            Do not forget to click on
            <span style="font-weight: bold">Save & Next</span> after answering
            the last question before the countdown timer reaches zero.
          </li>
          <li>
            The questions will be displayed on the screen one at a time in
            English language only.
          </li>
          <li>
            Each question will have 4 options, out of which one will be the
            correct answer and the candidate has to select one option.
          </li>
          <li>
            <span style="font-weight: bold">Details of the Question Papers:</span>
          </li>
        </ol>
        <table>
          <thead>
            <tr>
              <th scope="col">S. No.</th>
              <th scope="col">Section Name</th>
              <th scope="col">No. of objective type Questions</th>
              <th scope="col">Marks</th>
              <th scope="col">Marks per Question</th>
              <th scope="col">Remarks</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mathematics</td>
              <td>60</td>
              <td>60</td>
              <td>1</td>
              <td>Common to all branches</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Physics</td>
              <td>60</td>
              <td>60</td>
              <td>1</td>
              <td>Common to all branches</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Chemistry</td>
              <td>60</td>
              <td>60</td>
              <td>1</td>
              <td>Common to all branches</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style="margin: 10px; display: flex ;">
        <input style=" display: flex;align-content: baseline ;" type="checkbox" name="conditions" id="termsCheckbox"
          onchange="toggleButton()" required>
        <p style="margin: 10px;">I have read and understood the instructions. All computer hardware allotted to me are
          in proper working condition. I declare that I am - not in possession of/ not wearing / not carrying any
          prohibited gadget like mobile phone, bluetooth devices etc. /any prohibited material with me into the
          Examination Hall.I agree that in case of not adhering to the instructions, I shall be liable to be debarred
          from this Test and/or to disciplinary action, which may include ban from future Tests / Examinations</p>

      </div>
      <div>
        <div style="border-top: 2px soild black; height: 50px; display: flex;">
          <a href="instructions.jsp"
            style="margin-top: 10px; margin-left: 50px; justify-content: center; width: 100px ; height:30px; text-decoration: none; border: 2px solid black; border-radius: 3px; padding-top: 7px; padding-left: 20px; color:black;">
            < Previous</a>
            
             <button href="${pageContext.request.contextPath}/examTestStart"
                style="margin-top: 10px; margin-left : 67% ;display: flex; justify-content: center; width: 200px ; height:30px; text-decoration: none; border-radius: 3px; padding-top: 7px; background-color: rgb(221, 218, 218); color: white; border:none;"
                id="submitButton" disabled>I am ready to begin</button>
                
                
<%--             <a href="${pageContext.request.contextPath}/examTestStart">  <button 
                style="margin-top: 10px; margin-left : 67% ;display: flex; justify-content: center; width: 200px ; height:30px; text-decoration: none; border-radius: 3px; padding-top: 7px; background-color: rgb(221, 218, 218); color: white; border:none;"
                id="submitButton" disabled>I am ready to begin</button>
                </a> --%>
        </div>
      </div>
    </div>
    
    <% 
			String name = (String)session.getAttribute("userName");
			String photo = (String)session.getAttribute("photo");
			
			%>
    <div class="right" style="width: 25% ; height:30%;">
      <img src="<%= photo %>" alt="" style="margin-top: 24%; margin-left: 26%; width: 170px" />
      <p style="text-align: center"><%= name %></p>
    </div>
  </div>
  <script>
  
  
    function toggleButton() {
      var checkbox = document.getElementById("termsCheckbox");
      var button = document.getElementById("submitButton");

      if (checkbox.checked) {
        button.disabled = false;
        button.style.backgroundColor = "#007bff";
      } else {
        button.disabled = true;
        button.style.backgroundColor = "rgb(221, 218, 218";

      }
    }
    
    	document.getElementById("submitButton").addEventListener("click", function() {
    	  window.location.href = "${pageContext.request.contextPath}/examTestStart";
    	});

    
  </script>
</body>

</html>