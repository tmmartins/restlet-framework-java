package org.restlet.example.book.restlet.ch08.sec1;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Resource corresponding to a mail received or sent with the parent mail
 * account. Leverages FreeMarker template engine.
 */
public class MailServerResource extends ServerResource {

    @Override
    protected Representation get() throws ResourceException {
        // Create the mail bean
        Mail mail = new Mail();
        mail.setId((String) getRequestAttributes().get("mailId"));
        mail.setStatus("received");
        mail.setSubject("Message to self");
        mail.setContent("Doh!");
        mail.setAccountRef(new Reference(getReference(), "..").getTargetRef()
                .toString());

        // Load the FreeMarker template
        Representation mailFtl = new ClientResource(
                LocalReference.createClapReference(getClass().getPackage())
                        + "/Mail.ftl").get();

        // Wraps the bean with a FreeMarker representation
        TemplateRepresentation result = new TemplateRepresentation(mailFtl,
                mail, MediaType.TEXT_HTML);
        return result;
    }

    @Put
    public String store(Form form) {
        for (Parameter entry : form) {
            System.out.println(entry.getName() + "=" + entry.getValue());
        }

        return "Mail received!";
    }
}
