import {useState} from 'react'
import HeaderBar from "./components/HeaderBar.jsx";
import MainView from "./views/MainView.jsx";


function App() {


  return (
    <>
      <div className="h-screen w-screen flex justify-center">
        <div style={{width: '70%'}} className='flex flex-col'>
          <div>
            <HeaderBar/>
          </div>
          <div className='flex-1'>
            <MainView/>
          </div>
        </div>
      </div>
    </>
  )
}

export default App
