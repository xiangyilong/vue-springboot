// import store from '@/store'
import * as THREE from 'three'
// import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls'
// import {now} from 'three/addons/libs/tween.module'
// import dat from 'dat.gui/src/dat'

// import {homeViewPositions} from "./"

export default function init () {
  // let visited = store.state.visited;
  const scene = new THREE.Scene()
  const camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000)
  camera.position.set(5, 10, 10)
  const renderer = new THREE.WebGL1Renderer({
    antialias: true,
    logarithmicDepthBuffer: true
  })
  renderer.setSize(window.innerWidth, window.innerHeight)
  renderer.outputEncoding = THREE.sRGBEncoding

  var dom = document.getElementById('container')
  dom && dom.appendChild(renderer.domElement)
  // var control = new OrbitControls(camera, renderer.domElement)
  // var controls = {
  //   positionX: 0,
  //   positionY: 0,
  //   positionZ: 0
  // }
  var starsGeometry = new THREE.CircleGeometry()
  for (var i = 0; i < 10000; i++) {
    var star = new THREE.Vector3()
    star.x = THREE.Material.randFloatSpread(2500)
    star.y = THREE.Material.randFloatSpread(2000)
    star.z = THREE.Material.randFloatSpread(2500)

    starsGeometry.vertices.push(star)
  }
  var starMeterial = new THREE.PointsMaterial({
    color: 0x888888
  })
  var starField = new THREE.Points(starsGeometry, starMeterial)
  scene.add(starField)

  var beforeData = new Date()
  // const geometry = new THREE.BoxGeometry(1, 1, 1)
  // var materialBasic = new THREE.MeshBasicMaterial({
  //   color: 0xffffff,
  //   wireframe: true
  // })
  // var materialNormal = new THREE.MeshNormalMaterial()
  // var cube = new THREE.Mesh(geometry, [
  //   materialBasic,
  //   materialNormal
  // ])
  // camera.lookAt(cube.position)
  // scene.add(cube)

  var spotlight = new THREE.SpotLight(0xffffff)
  spotlight.position.set(0, 20, 20)
  spotlight.intensity = 5
  scene.add(spotlight)

  // var axes = new THREE.AxesHelper(6)
  // scene.add(axes)

  // renderer.render(scene, camera)

  function animate () {
    var nowDate = new Date()
    console.log('nowdata', nowDate)

    var t = nowDate - beforeData
    beforeData = nowDate
    camera.rotateX(0.00005 * t)
    camera.rotateY(-0.0001 * t)
    camera.rotateZ(0.00005 * t)
    requestAnimationFrame(animate)
    // cube.rotation.x += 0.01
    // cube.rotation.y += 0.01
    // cube.position.set(2, 5, 6)
    // control.update()
    renderer.render(scene, camera)
  }
  animate()
  // var spriteMaterialNormal = new THREE.SpriteMaterial({color: 0x00ffff})
  // var spriteNormal = new THREE.Sprite(spriteMaterialNormal)
  // spriteNormal.position.set(-30, 10, 0)
  // spriteNormal.scale.set(5, 5, 1)
  // scene.add(spriteNormal)
  // var gui = new dat.GUI()
  // gui.add(controls, 'positionX', -10, 10).onChange(updatePosition)
  // gui.add(controls, 'positionY', -10, 10).onChange(updatePosition)
  // gui.add(controls, 'positionZ', -1, 1).onChange(updatePosition)
  // function updatePosition () {
  //   cube.position.set(controls.positionX, controls.positionY, controls.positionZ)
  // }
}
