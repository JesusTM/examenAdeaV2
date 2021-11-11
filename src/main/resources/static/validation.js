function checkPasswordMatch(fieldConfirmPassword) {
    if (fieldConfirmPassword.value != $("#newPassword").val()) {
        fieldConfirmPassword.setCustomValidity("Passwords do not match!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}