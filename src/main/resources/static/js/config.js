require.config({
	baseUrl: "js",
	paths: {
		"parsleyjs": "/webjars/parsleyjs/2.1.2/parsley.min",
		"bootstrap-datatables": "/webjars/datatables-plugins/1.10.10/integration/bootstrap/3/dataTables.bootstrap.min"
	},
	shim: {
		"parsleyjs": {
			deps: ["jquery"]
		},
		"bootstrap-datatables": {
			deps: ["datatables"]
		}
	}
});