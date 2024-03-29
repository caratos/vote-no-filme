package br.com.votefilme.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;

	public IndexController(Result result) {
		this.result = result;
	}

	public void index() {
		result.include("variable", "VRaptor!");
	}
	
	@Path("/abc")
	public void other() {
		result.redirectTo(IndexController.class).index();
	}
}
