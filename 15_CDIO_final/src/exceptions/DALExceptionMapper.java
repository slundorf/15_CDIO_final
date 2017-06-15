package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DALExceptionMapper implements ExceptionMapper<DALException> {
		public Response toResponse(DALException ex) {
			return Response.status(401).
				entity(ex.getMessage()).
				type("text/plain").
				build();
		}

}
