import { Button, Form } from "react-bootstrap";

export default function Signup() {
  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100">
      <Form className="border rounded-3 mt-2 mb-5 mx-2 p-3">
        <Form.Group className="mb-3">
          <Form.Label>
            <strong>Username</strong>
          </Form.Label>
          <Form.Control type="text" required />
          <Form.Text className="text-muted">
            Must be 1~100 characters long and must not contain spaces or special
            characters
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>
            <strong>Password</strong>
          </Form.Label>
          <Form.Control type="password" required />
          <Form.Text className="text-muted">
            Must be 1~100 characters long and must not contain spaces
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>
            <strong>Confirm Password</strong>
          </Form.Label>
          <Form.Control type="password" required />
        </Form.Group>
        <Button variant="primary" className="w-100 mt-3">
          Sign up
        </Button>
      </Form>
    </div>
  );
}
