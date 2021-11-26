package gr.pr.accepted.sportbettingodds.exception;

import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SportBettingOddsExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<MatchOddsResponse> dataNotFound(EmptyResultDataAccessException e) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setStatusCode(HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MatchOddsResponse> internalServerError(Exception e) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
