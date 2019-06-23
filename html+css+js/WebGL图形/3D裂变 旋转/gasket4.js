
var canvas;
var gl;
var timer=null;
var points = [];
var colors = [];
var program;
var NumTimesToSubdivide = 5;

var xAxis = 0;
var yAxis = 1;
var zAxis = 2;

var axis = 1;
var theta = [ 0, 0, 0 ];
var thetaLoc;
var frame=null;

var vertices = [
    vec4(  0.0000,  0.0000, -1.0000 ,1.0),
    vec4(  0.0000,  0.9428,  0.3333 ,1.0),
    vec4( -0.8165, -0.4714,  0.3333 ,1.0),
    vec4(  0.8165, -0.4714,  0.3333 ,1.0)
];
window.onload = function () {
    canvas = document.getElementById( "gl-canvas" );
    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }
    //
    //  Initialize our data for the Sierpinski Gasket
    //
    //  Load shaders and initialize attribute buffers

    program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

    init();
}
function init(){
    points = [];
    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );
    gl.enable(gl.DEPTH_TEST);

    divideTetra( vertices[0], vertices[1], vertices[2], vertices[3],
        NumTimesToSubdivide++);
    if(NumTimesToSubdivide==7)
        NumTimesToSubdivide=0;

    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW );

    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor );

    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW );


    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );
    thetaLoc = gl.getUniformLocation(program, "theta");

    document.getElementById( "xButton" ).onclick = function () {
        axis = xAxis;
    };
    document.getElementById( "yButton" ).onclick = function () {
        axis = yAxis;
    };
    document.getElementById( "zButton" ).onclick = function () {
        axis = zAxis;
    };
    if(frame!=null)
        cancelAnimationFrame(frame);
    render();
}
var then;
function render(now)
{
    if (isNaN(now)) {
        window.requestAnimFrame(render);
        return;
    }

    // Convert the time to seconds
    now *= 0.001;
    // Subtract the previous time from the current time
    var deltaTime = now - then;
    // Remember the current time for the next frame.
    then = now;
    console.log(" -FPS- " + 1/deltaTime);
    gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    theta[axis] += 2.0;
    gl.uniform3fv(thetaLoc, theta);
    gl.drawArrays( gl.TRIANGLES, 0,points.length );
    frame=requestAnimFrame( render );
}

function triangle( a, b, c, color )
{
    var baseColors = [
        vec4(1.0, 0.0, 0.0, 1.0),
        vec4(0.0, 1.0, 0.0, 1.0),
        vec4(0.0, 0.0, 1.0, 1.0),
        vec4(0.0, 0.0, 0.0, 1.0)
    ];

    colors.push( baseColors[color] );
    points.push( a );
    colors.push( baseColors[color] );
    points.push( b );
    colors.push( baseColors[color] );
    points.push( c );
}

function tetra( a, b, c, d )
{
    triangle( a, c, b, 0 );
    triangle( a, c, d, 1 );
    triangle( a, b, d, 2 );
    triangle( b, c, d, 3 );
}

function divideTetra( a, b, c, d, count )
{
    if ( count === 0 ) {
        tetra( a, b, c, d );
    }

    else {
        var ab = mix( a, b, 0.5 );
        var ac = mix( a, c, 0.5 );
        var ad = mix( a, d, 0.5 );
        var bc = mix( b, c, 0.5 );
        var bd = mix( b, d, 0.5 );
        var cd = mix( c, d, 0.5 );

        --count;
        
        divideTetra(  a, ab, ac, ad, count );
        divideTetra( ab,  b, bc, bd, count );
        divideTetra( ac, bc,  c, cd, count );
        divideTetra( ad, bd, cd,  d, count );
    }
}

function start(){
    if(timer==null)
        timer=setInterval(init,1000);
}

function pause(){
    clearInterval(timer);
    timer=null;
}
function exit(){
    clearInterval(timer);
    NumTimesToSubdivide=6;
    init();
    timer=null;
}