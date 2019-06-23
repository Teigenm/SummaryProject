
var canvas;
var gl;
var points = [];
var numtime = 0;
var timer=null;
var vl=1000;
var vertices= [
    vec2( -1, -1 ),
    vec2(  0,  1 ),
    vec2(  1, -1 )
];
var program;

window.onload = function ()
{   canvas = document.getElementById( "gl-canvas" );
    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }
    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );
    program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

    init();
}

function init(){

    points = [];
    divideTriangle( vertices[0], vertices[1], vertices[2],
        numtime++);
    if(numtime==9) {
        numtime=0;
    }
    if(vl>=100)
        vl-=20;
    if(vl%100==0){
        clearInterval(timer);
        timer=null;
        timer=setInterval(init,vl);
    }
    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW );

    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );

    render();
}

function triangle( a, b, c )
{
    points.push( a, b, c );
}

function divideTriangle( a, b, c, count )
{

    // check for end of recursion
    
    if ( count === 0 ) {
        triangle( a, b, c );
    }
    else {
    
        //bisect the sides
        
        var ab = mix( a, b, 0.5 );
        var ac = mix( a, c, 0.5 );
        var bc = mix( b, c, 0.5 );

        --count;

        // three new triangles
        
        divideTriangle( a, ab, ac, count );
        divideTriangle( c, ac, bc, count );
        divideTriangle( b, bc, ab, count );
    }
}

function start(){
    if(timer==null)
        timer=setInterval(init,vl);
}

function pause(){
    clearInterval(timer);
    timer=null;
}

function render()
{
    gl.clear( gl.COLOR_BUFFER_BIT );
    gl.drawArrays( gl.TRIANGLES, 0, points.length );
}