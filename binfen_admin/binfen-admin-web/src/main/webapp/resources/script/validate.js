//����֤��¼����֤
function checkIdcard(idcard) {
    var Errors = new Array(
            'yes',
            "����֤����λ������!",
            "����֤����������ڳ�����Χ���зǷ��ַ�!",
            "����֤����У�����!",
            "����֤�����Ƿ�!"
            );
    var area = {11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",21:"����",22:"����",23:"������",31:"�Ϻ�",32:"����",33:"�㽭",34:"����",35:"����",36:"����",37:"ɽ��",41:"����",42:"����",43:"����",44:"�㶫",45:"����",46:"����",50:"����",51:"�Ĵ�",52:"����",53:"����",54:"����",61:"����",62:"����",63:"�ຣ",64:"����",65:"�½�",71:"̨��",81:"���",82:"����",91:"����"}

    var idcard,Y,JYM;
    var S,M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //��������
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
    //���ݺ���λ������ʽ����
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//���Գ������ڵĺϷ���
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//���Գ������ڵĺϷ���
            }
            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
            //18λ���ݺ�����
            //�������ڵĺϷ��Լ��
            //��������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
            //ƽ������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//����������ڵĺϷ����������ʽ
            } else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//ƽ��������ڵĺϷ����������ʽ
            }
            if (ereg.test(idcard)) {//���Գ������ڵĺϷ���
                //����У��λ
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                        + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                        + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                        + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                        + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                        + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                        + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                        + parseInt(idcard_array[7]) * 1
                        + parseInt(idcard_array[8]) * 6
                        + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);//�ж�У��λ
                if (M == idcard_array[17] || "X" == idcard_array[17] || 'x' == idcard_array[17]) return Errors[0]; //���ID��У��λ
                else return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}

//С�� ��֤
function checkPrice(num,maxNum) {
	var array=new Array('yes','�Ƿ�����!','����2λС��!','����С�ڵ���:'+maxNum+' ','����λ����!');
	if(isNaN(num)){
		return array[1];
	}
    if(parseFloat(num)<0){
		return array[4];
	}
	if(num.indexOf('.')!=-1){
		var splitArray=num.split('.');
		if(splitArray[1].length>2){
			return array[2];
		}
	}
    if(parseFloat(num)>parseFloat(maxNum)){
        return array[3];
    }
	return array[0];
}

//С�� ��֤
function checkPrice2(num,maxNum) {
	var array=new Array('yes','�Ƿ�����!','����2λС��!','����С��:'+maxNum+' ','����λ����!');
	if(isNaN(num)){
		return array[1];
	}
    if(parseFloat(num)<0){
		return array[4];
	}
	if(num.indexOf('.')!=-1){
		var splitArray=num.split('.');
		if(splitArray[1].length>2){
			return array[2];
		}
	}
    if(parseFloat(num)>=parseFloat(maxNum)){
        return array[3];
    }
	return array[0];
}


function checkPrice3(num) {
        	var array=new Array('yes','�˿��������ʽ���Ϸ�!','����2λС��!','����Ϊ����!');
        	if(isNaN(num)){
        		return array[1];
        	}
            if(parseFloat(num)<0){
        		return array[3];
        	}
        	if(num.indexOf('.')!=-1){
        		var splitArray=num.split('.');
        		if(splitArray[1].length>2){
        			return array[2];
        		}
        	}

        	return array[0];
        }