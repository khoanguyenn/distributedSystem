function calcNumbers(result){
    form.displayResult.value=form.displayResult.value+result;
}
function calculateNumber(result) {
    let numbers = form.displayResult.value.split(/[\s\+\-\*\/]+/); //This to split the numbers
    let operator = form.displayResult.value.match(/\D/) //This to find the operator
    fetch("http://localhost:8080/calculatorservice?"+
        "a=" + encodeURIComponent(numbers[0]) +
        "&b=" + encodeURIComponent(numbers[1]) +
        "&operator=" + encodeURIComponent(operator))
        .then(response => response.json())
        .then((data) => {
            form.displayResult.value = data; //Display to the screen
        })
}