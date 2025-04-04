<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Summay</title>
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
        
        tbody tr{
          text-align: center;
        }
  
        tfoot td {
          font-weight: bold;
        }
      </style>
</head>
<body style="margin: auto;">

	<% 
			String name = (String)session.getAttribute("userName");
			String photo = (String)session.getAttribute("photo");
			
			int[] maths =(int[])request.getAttribute("maths");
    		int[] physics =(int[])request.getAttribute("physics");
    		int[] chemistry =(int[])request.getAttribute("chemistry");
			
	%>
    <div class="top" style="border-bottom: 2px solid rgb(227, 225, 225);">

        <div style="border-top:2px solid rgb(227, 225, 225); border-top:2px solid rgb(227, 225, 225);border-bottom: none ;  border-right:none; height: 20%; width: 25%; margin-left: 75% ; display: flex; align-items: center;">
            <img src="<%= photo %>" alt="Student Image" style="height: 85% ; margin: 10px; border: 2px solid rgb(227, 225, 225);">
            <div style="padding-left: 20px; width: 80%; display: flex; height: 80%; align-items: center;"><%= name %></div>
        </div>

    </div>
    <div class="bottom">

            <p style="text-align: center; font-size: 24px;"> Exam Summary</p>
            <div style="overflow-y: auto ; margin: 20px 10%;height: 50%;">
                
                <table>
                    <thead>
                        <tr style="height: 50px;">
                            <th scope="col" style="width: 200px;">Section Name</th>
                            <th scope="col" style="width: 200px;">No. of Questions</th>
                            <th scope="col" style="width: 150px;">Answered</th>
                            <th scope="col" style="width: 150px;">Not Answered</th>
                            <th scope="col" style="width: 150px;">Mark for Review</th>
                            <th scope="col" style="width: 180px; ">Ansered and Marked for Review (will be considered for evaluation)</th>
                            <th scope="col" style="width: 150px;">Not Visited</th>
                        </tr>
                        
                    </thead>
                    <tbody>
                    	 	<tr>
	                            <th scope="row"> Mathematics </th>
	                            <td>60</td>
	                            <td> <%= maths[2] %> </td>
	                            <td> <%= maths[1] %> </td>
	                            <td> <%= maths[3] %> </td>
	                            <td> <%= maths[4] %> </td>
	                            <td> <%= maths[0] %> </td>
	                        </tr>
	                        
	                        <tr>
	                            <th scope="row">Physics</th>
	                            <td>60</td>
	                            <td> <%= physics[2] %> </td>
	                            <td> <%= physics[1] %> </td>
	                            <td> <%= physics[3] %> </td>
	                            <td> <%= physics[4] %> </td>
	                            <td> <%= physics[0] %> </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">Chemistry</th>
	                            <td>60</td>
                            	<td> <%= chemistry[2] %> </td>
	                            <td> <%= chemistry[1] %> </td>
	                            <td> <%= chemistry[3] %> </td>
	                            <td> <%= chemistry[4] %> </td>
	                            <td> <%= chemistry[0] %> </td>
	                        </tr>
	                        
                    </tbody>
                </table>

            </div>
            <div style="align-items: center; text-align: center;">
                <p style=" border-top: 2px solid rgb(227, 225, 225); padding-top: 10px; ">Are you sure to summit this Group? Click "Yes" to proceed,Click "No" to go back.</p>
                <p>Dear Candidate.Once the Group is submitted,you cannot revisit and edit your responses.</p>

            </div>
            <div style="display: flex; align-items: center; justify-content: center;">
                <a href="html/student/submit.jsp" style="height: 25px; width: 80px; border:1px solid rgb(227, 225, 225); border-radius: 4px;  color:black; text-align: center;  padding-top: 5px ;  text-decoration: none; margin-right: 5px; font-size: 18px;">Yes</a>
                <a href="${pageContext.request.contextPath}/examTestStart?subject=maths" style="height: 25px; width: 80px; border:1px solid rgb(227, 225, 225); border-radius: 4px;  color:black; text-align: center; padding-top: 5px; text-decoration: none; margin-left: 5px; font-size: 18px;">No</a>
            </div>


    </div>
</body>
</html>