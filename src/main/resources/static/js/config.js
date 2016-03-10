require.config({
	baseUrl: "/js",
	paths: {
		"jquery": "vendor/jquery/2.2.0/jquery.min",
		"jquery-ui": "vendor/jquery-ui/1.11.4/jquery-ui.min",
		"parsleyjs": "vendor/parsleyjs/2.3.5/parsley.min",
		"bootstrap": "vendor/bootstrap/3.3.6/js/bootstrap.min",
		"datatables.net": "vendor/datatables/1.10.11/js/jquery.dataTables.min",
		"datatables-bootstrap": "vendor/datatables/1.10.11/js/dataTables.bootstrap.min",
		"datatables-date": "vendor/datatables/1.10.11/plug-ins/sorting/date-dd-MMM-yyyy"
	},
	shim: {
		"jquery-ui": {
			deps: ["jquery"]
		},
		"parsleyjs": {
			deps: ["jquery"]
		},
		"bootstrap": {
			deps: ["jquery"]
		},
		"datatables.net": {
			deps: ["jquery"]
		},
		"datatables-date": {
			deps: ["datatables.net"]
		},
		"datatables-bootstrap": {
			deps: ["datatables.net", "datatables-date"]
		}
	}
});