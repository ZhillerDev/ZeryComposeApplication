import React from "react";

const MainView = () => {
	return (
		<div className="p-6 flex justify-center items-center flex-col md:flex-row w-full h-full">
			<div className="md:w-1/2">
				<div className="font-bold text-5xl Mcolor">New Board</div>
				<p className="mt-4 text-1xl Mcolor">Think for a new board for pad</p>
				<p className="mt-2 text-1xl Mcolor">Free to use</p>
				<p className="mt-2 md-2"> </p>
				<div className="mt-3 p-4 gradient-button transition cursor-pointer w-60">
					<a
						href="https://zhiyiyi.lanzoul.com/ieb9R1s6k9vg"
						target="_blank"
						rel="noopener noreferrer"
					>
						Download App
					</a>
				</div>
			</div>
			<div className="md:w-1/2 mt-custom">
				<img src="https://web-onx01.oss-cn-beijing.aliyuncs.com/show.png" alt="Image" className="image-with-shadow" />
			</div>
		</div>
	);
};

export default MainView;
