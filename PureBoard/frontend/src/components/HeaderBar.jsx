import React from 'react';
import "./comp.css"

const HeaderBar = () => {
  return (
    <div className='flex justify-between items-center'>
      <div className='p-6 font-bold Hcolor'>
        PureBoard
      </div>
      <div className='p-4 text-gray-400 hover:text-gray-200 transition cursor-pointer Hcolor'>
        About
      </div>
    </div>
  );
};

export default HeaderBar;
