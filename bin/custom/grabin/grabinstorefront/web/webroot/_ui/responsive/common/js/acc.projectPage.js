ACC.projectPage = {

	_autoload: [
		"bindLink"
	],

	bindLink: function () {
		// console.log("Aeeee");


		// $(document).on("click", ".js-project-approve", function (e) {
		//     e.preventDefault();
		//
		//     console.log("aproveee 2");
		// });
	}
};


$(document).ready(function () {

	console.log("11111111111111111111111")

	let projectTable = document.getElementById('projectTable');
	if(projectTable) {
		console.log("Tabela existe!");

		fetch('api/project')
			.then(response => response.json())
			.then(data => console.log(data));
	}

	function fetchSingle(id) {
		console.log("fetching project with id " + id);

		fetch('api/project/' + id)
			.then(response => response.json())
			.then(data => console.log(data));
	}

	document.querySelectorAll('.js-project-approve')
		.forEach(function (button) {
			button.addEventListener('click', function () {
				console.log("aprove");
				console.log(button);
				console.log(button.id);
				console.log(button.dataset.id);

				$.ajax({
					method: 'POST',
					url: 'api/project/approve/' + button.dataset.id,
					success: function (result) {
						console.log("Check prices with success");

						fetchSingle(button.dataset.id);
					},
					complete: function (result) {
						console.log("Check prices completed");
					}
				});
			});
		});


	document.querySelectorAll('.js-project-ready')
		.forEach(function (button) {
			button.addEventListener('click', function () {
				console.log("ready 2");
				console.log(button);

				$.ajax({
					method: 'POST',
					url: 'api/project/ready/' + button.dataset.id,
					success: function (result) {
						console.log("Check prices with success");

						fetchSingle(button.dataset.id);
					},
					complete: function (result) {
						console.log("Check prices completed");
					}
				});
			});
		});

	document.querySelectorAll('.js-project-delete')
		.forEach(function (button) {
			button.addEventListener('click', function () {
				console.log("delete 2");
			});
		});

	// $('.js-cartItemDetailBtn').click(function(event) {
	//     event.stopPropagation();
	//     var thisDetailGroup =  $(this).parent('.js-cartItemDetailGroup');
	//     $(thisDetailGroup).toggleClass('open'); //only in its parent
	//     if ( $(thisDetailGroup).hasClass('open') )  {
	//         //close all if not this parent
	//         $('.js-cartItemDetailGroup').not( thisDetailGroup ).removeClass('open');
	//         //change aria
	//         $('.js-cartItemDetailBtn').attr('aria-expanded', 'true');
	//
	//     } else {
	//         $('.js-cartItemDetailBtn').attr('aria-expanded', 'false');
	//     }
	//     $(document).click( function(){
	//         $(thisDetailGroup).removeClass('open');
	//     }); // closes when clicking outside this div
	// });
	//
	// //enable comment for this item only
	// $('.js-entry-comment-button').click(function(event) {
	//     event.preventDefault();
	//     var linkID = $(this).attr('href');
	//     $( linkID ).toggleClass('in');
	//     $( thisDetailGroup ).removeClass('open');
	// });
});

