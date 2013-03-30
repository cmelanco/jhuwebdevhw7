function setTourLength(tour)
{
   var dur = document.hikepicker.numdays;
   dur.options.length = 0;
   if (tour == "") {
      dur.options[dur.options.length] = new Option('Please select Tour first','');
   }
   if (tour == "gardiner") {
      dur.options[dur.options.length] = new Option('3 days','3');
      dur.options[dur.options.length] = new Option('5 days','5');
   }
   if (tour == "hellroaring") {
      dur.options[dur.options.length] = new Option('2 days','2');
      dur.options[dur.options.length] = new Option('3 days','3');
      dur.options[dur.options.length] = new Option('4 days','4');
   }
   if (tour == "beaten") {
      dur.options[dur.options.length] = new Option('5 days','5');
      dur.options[dur.options.length] = new Option('7 days','7');
   }
}