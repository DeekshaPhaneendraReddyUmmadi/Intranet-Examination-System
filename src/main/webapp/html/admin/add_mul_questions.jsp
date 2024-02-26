<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Question</title>
    <link href="../../css/admin/add_mul_questions.css" rel="stylesheet">
</head>

<body>
    <div class="uploadForm">
        <form action="<%= request.getContextPath()%>/multiImage" method="post" enctype="multipart/form-data">
            <h1 class="u_q">Upload Question</h1>
            <div class="questionContainer">
                <!-- Input field for selecting an image -->
                <div class="question">
                    <label class="questionLabel" for="image1">Question</label>
                    <input type="file" name="image1" accept="image/*" onchange="showQuestionPreview(event)" id="image1">
                </div>
                <!-- question preview container -->
                <img id="question-preview" src="" style="display: none;">
                <br>
                <div class="options-container">
                    <div class="option">
                        <input type="file" name="image2" id="option1" accept="image/*" onchange="previewImage(this, 'preview1')">
                        <label for="option1">Option 1</label>
                        <img id="preview1">
                    </div>
                    <div class="option">
                        <input type="file" name="image3" id="option2" accept="image/*" onchange="previewImage(this, 'preview2')">
                        <label for="option2">Option 2</label>
                        <img id="preview2">
                    </div>
                    <div class="option">
                        <input type="file" name="image4" id="option3" accept="image/*" onchange="previewImage(this, 'preview3')">
                        <label for="option3">Option 3</label>
                        <img id="preview3">
                    </div>
                    <div class="option">
                        <input type="file" name="image5" id="option4" accept="image/*" onchange="previewImage(this, 'preview4')">
                        <label for="option4">Option 4</label>
                        <img id="preview4">
                    </div>
                </div>
                <br><br><br>
                <label for="correct_option">Correct Option :</label>
                <input type="number" id="correct_option" name="correct_option"><br><br>
                <div class="button-container">
                    <input class="uploadButton" type="submit" name="upload" value="Upload">
                    <input class="uploadAndNewButton" type="submit" name="upload" value="Upload & New">
                </div>
            </div>
        </form>
    </div>
    <script>
    // Function to display image preview
    function showQuestionPreview(event) {
        const file = event.target.files[0];
        if (file) {
            const imagePreview = document.getElementById('question-preview');
            imagePreview.style.display = 'block';
            imagePreview.src = URL.createObjectURL(file);
        }
    }

    function previewImage(input, previewId) {
        const preview = document.getElementById(previewId);
        const file = input.files[0];
        const reader = new FileReader();

        reader.onloadend = function() {
            preview.src = reader.result;
            preview.style.display = 'block';
        }

        if (file) {
            reader.readAsDataURL(file);
        } else {
            preview.src = '';
            preview.style.display = 'none';
        }
    }
    </script>
</body>

</html>