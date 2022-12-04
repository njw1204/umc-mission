import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { MenuBar } from "../components/MenuBar";

export default function New() {
  return (
    <>
      <MenuBar />
      <Container fluid="md" className="mt-4">
        <Row>
          <Col>
            <Form className="border rounded-3 mt-2 mb-5 mx-2 p-3">
              <Form.Group className="mb-3">
                <Form.Label>
                  <strong>File Name</strong>
                </Form.Label>
                <Form.Control type="text" required />
                <Form.Text className="text-muted">
                  Must be ~300 characters long and must not be blank
                </Form.Text>
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>
                  <strong>Description</strong>
                </Form.Label>
                <Form.Control type="text" required />
                <Form.Text className="text-muted">
                  Must be ~5000 characters long
                </Form.Text>
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>
                  <strong>Code</strong>
                </Form.Label>
                <Form.Control as="textarea" rows={5} required />
              </Form.Group>
              <Button variant="primary" className="w-100 mt-3">
                Submit
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </>
  );
}
