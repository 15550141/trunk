// 为了样式而加的JS
var formClassName="panel_form";
var gridClassName="panel_grid";
var searchClassName="panel_search";
var formLabelName="TdLabel";
var formValueName="TdValue";




processStype();


function processStype()
{
   setControl();
   setFormStyle();
}

function setControl()
{
  var objs=document.getElementsByTagName('input');
  for(var i=0;i<objs.length;i++)
  {
     if(objs[i].className!='')continue;
     if(objs[i].type=='text')
     {
        objs[i].className='txt';
        //objs[i].onblur=function(){this.className='txt';}
        //objs[i].onfocus=function(){this.className='txtFocus';}
     }
     if(objs[i].type=='button' || objs[i].type=='submit')
     {
        var vLen=objs[i].value.length;
        if(vLen<=2)objs[i].className='btn1';
        else objs[i].className='btn2';
     }
  }
}
function setFormStyle()
{
   var tbs=document.getElementsByTagName('table');
   for(var i=0;i<tbs.length;i++)
   {
      if(tbs[i].className!='')continue;
      if(tbs[i].parentNode.className==formClassName || tbs[i].parentNode.className==searchClassName)
      {
         
         for(var j=0;j<tbs[i].rows.length;j++)
         {
            if(tbs[i].rows.lenght==1){tbs[i].rows[0].className=formValueName;break;}
            for(var k=0;k<tbs[i].rows[j].cells.length;k++)
            {
               if(tbs[i].rows[j].cells[k].className!='')continue;
               if(k%2==0){tbs[i].rows[j].cells[k].className=formLabelName;}
               else{tbs[i].rows[j].cells[k].className=formValueName;}
            }
         }
         
      }
      
      if(tbs[i].parentNode.parentNode.className==gridClassName || tbs[i].parentNode.className==gridClassName)
      {
         for(var j=1;j<tbs[i].rows.length;j++)
         {
            tbs[i].rows[j].onmouseover=function(){this.className='trOver';}
            tbs[i].rows[j].onmouseout=function(){this.className='';}
            
         }
         
      }
      
      
   }
}
