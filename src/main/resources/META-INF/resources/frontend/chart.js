import {customElement} from 'lit/decorators.js';

class Chart extends Element{ 

    // set the data
    
    
    
      static get styles() {
    return css`
       html, body, #container {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
    `;
  }
      constructor(){
		  
	
    var data = {
      header: ["Name", "Death toll"],
      rows: [
        ["San-Francisco (1906)", 1500],
        ["Messina (1908)", 87000],
        ["Ashgabat (1948)", 175000],
        ["Chile (1960)", 10000],
        ["Tian Shan (1976)", 242000],
        ["Armenia (1988)", 25000],
        ["Iran (1990)", 50000]
    ]};

    // create the chart
   var chart = anychart.bar();

    // add data
    chart.data(data);

    // set the chart title
    chart.title("The deadliest earthquakes in the XXth century");


  // draw
  chart.container("container");
  chart.draw();

}
 render(){
        return html`
        <script src="https://cdn.anychart.com/releases/8.0.0/js/anychart-base.min.js"></script>
<div id="container"></div>
`

}}
customElements.define('chart', Chart);