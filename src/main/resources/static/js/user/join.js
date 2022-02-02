(function () {
    'use strict'

    var form = document.querySelector('#join_frm');

    form.addEventListener('submit', function (event) {
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation(); //event객체의 버블링(이벤트가 연속하여 발생하는 버블 현상)을 제거해준다
        }
        form.classList.add('was-validated');
    }, false);

})(); //()() : funtion을 바로 호출하여라