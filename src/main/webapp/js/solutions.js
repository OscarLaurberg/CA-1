function onClickImageChange(){
    console.log('yo');
    if(document.getElementById('onClickChange').src == "https://coretours.se/wp-content/uploads/2018/07/Thunderdome-2019.png"){
        document.getElementById('onClickChange').src = "https://seeklogo.com/images/D/defqon-1-logo-8DE9EDE080-seeklogo.com.png";
    }else if(document.getElementById('onClickChange').src == "https://seeklogo.com/images/D/defqon-1-logo-8DE9EDE080-seeklogo.com.png"){
        document.getElementById('onClickChange').src = "https://www.pngkit.com/png/full/270-2705556_dominator-logo-dominator-festival-png.png";
}else if(document.getElementById('onClickChange').src == "https://www.pngkit.com/png/full/270-2705556_dominator-logo-dominator-festival-png.png"){
        document.getElementById('onClickChange').src = "https://uploads.scratch.mit.edu/users/avatars/27606864.png";
}else if(document.getElementById('onClickChange').src == "https://uploads.scratch.mit.edu/users/avatars/27606864.png"){
        document.getElementById('onClickChange').src = "https://www.wykop.pl/cdn/c3397992/HARDrychuCORE_qRk3LnvSJs,q250.jpg";
}else if(document.getElementById('onClickChange').src == "https://www.wykop.pl/cdn/c3397992/HARDrychuCORE_qRk3LnvSJs,q250.jpg"){
        document.getElementById('onClickChange').src = "https://moneycomb.nl/moneycomb.nl/wp-content/uploads/2018/04/Q-dance-logo.png";
}else if(document.getElementById('onClickChange').src == "https://moneycomb.nl/moneycomb.nl/wp-content/uploads/2018/04/Q-dance-logo.png"){
        document.getElementById('onClickChange').src = "https://coretours.se/wp-content/uploads/2018/07/Thunderdome-2019.png";
}
}


document.getElementById('onClickChange').addEventListener('click',onClickImageChange);