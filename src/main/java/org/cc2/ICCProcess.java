package org.cc2;

public interface ICCProcess<QM, REQ, RESP> {

    public QM init(REQ request, RESP response) throws Exception;

    public void before(QM m) throws Exception;
    
    public void proc(QM m);

    public void after(QM m);

    public void exception(QM m);

    public void release(QM m);

    default void process(REQ request, RESP response) throws Exception {
        QM m = null;
        try {
            m = init(request,response);
            before(m);
            proc(m);
            after(m);
        } catch (Exception e) {
            exception(m);
        } finally {
            release(m);
        }
    }

}
