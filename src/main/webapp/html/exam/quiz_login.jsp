<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Login</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="../../css/admin/admin.css" rel="stylesheet">
</head>
<body>

<div class="container login-container">
  <img src="../../images/admin/adphoto.jpeg" alt="Admin Logo" class="logo">
  <h2 class="text-center mb-4">Admin Login</h2>
  <form method="post" action="<%= request.getContextPath() %>/loginstudent"> 
    <div class="mb-3">
      <label for="user_name" class="form-label">Username</label>
      <input type="text" class="form-control" id="username" name="user_name" placeholder="Enter your username" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
    </div>
    <button type="submit" class="btn btn-primary btn-block">Login</button>
  </form>
</div>

<!-- Bootstrap JS and Popper.js (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
