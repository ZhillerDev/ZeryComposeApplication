import React from "react";

const MainView = () => {
	return (
		<div className="p-6 flex justify-center flex-col w-full h-full">
			<div className="font-bold text-5xl">New Board</div>
			<p className="mt-4 text-1xl">Think for a new board for pad</p>
			<p className="mt-2 text-1xl">Free to use</p>
			<div className="mt-3 p-4 rounded-2xl bg-sky-400 hover:bg-sky-800 transition cursor-pointer w-40">
				<a
					href="https://zhiyiyi.lanzoul.com/ieb9R1s6k9vg"
					target="_blank"
					rel="noopener noreferrer"
				>
					Download App
				</a>
			</div>
		</div>
	);
};

export default MainView;
