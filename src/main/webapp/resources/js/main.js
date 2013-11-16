function checkFields(){
    var idChecked=false;
    var radios = document.getElementsByName('id');

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            return true;
        }
    }
    alert("No element is chosen!");

    return false;;

   }