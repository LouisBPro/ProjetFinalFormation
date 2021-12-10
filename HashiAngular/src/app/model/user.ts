export class User {
  constructor(private _login?: string | undefined) {}

  /**
   * Getter login
   * @return {string }
   */
  public get login(): string | undefined {
    return this._login;
  }

  /**
   * Setter login
   * @param {string } value
   */
  public set login(value: string | undefined) {
    this._login = value;
  }
}
