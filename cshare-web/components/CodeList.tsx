import { Accordion, Badge, Form } from "react-bootstrap";

export function CodeList() {
  return (
    <Accordion>
      <Accordion.Item eventKey="1">
        <Accordion.Header>
          <div className="text-break me-3">
            <div>
              <span>abcde</span>&nbsp;/&nbsp;
              <strong className="me-2">print.js</strong>
              <Badge bg="secondary">Private</Badge>
            </div>
            <div className="text-muted small mt-1 mb-3">2022-11-21</div>
            <div>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
              enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
              reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur. Excepteur sint occaecat cupidatat non proident,
              sunt in culpa qui officia deserunt mollit anim id est laborum.
            </div>
          </div>
        </Accordion.Header>
        <Accordion.Body>
          <Form.Group>
            <Form.Control
              as="textarea"
              className="font-monospace"
              rows={20}
              value={"test test test"}
              readOnly
            />
          </Form.Group>
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
  );
}
